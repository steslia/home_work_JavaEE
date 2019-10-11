package ua.kiev.prog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {

    //Тут храняться фотки
    private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();

    //Подсовывает индексную страницу(та что первая выводиться)
    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping("/group_photo")
    public String listPhoto(Model model){
        Set<Long> listId = photos.keySet();
        model.addAttribute("photo_id", listId);
        return "group_photo";
    }

    @RequestMapping(value = "/delete_checkbox_photo", method = RequestMethod.POST)
    public String deleteCheckbox(Model model, @RequestParam(value = "deleteList[]", required = false) long[] delete){
        if (delete != null){
            for (long del : delete){
                photos.remove(del);
            }
        }

        Set<Long> listId = photos.keySet();
        model.addAttribute("photo_id", listId);
        return "group_photo";
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    //  @RequestParam обозначает что туда нужно передать параметр с POST запроса
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();

        try {
            long id = System.currentTimeMillis();
            photos.put(id, photo.getBytes());

            //Устанавливаем атрибут, для дальнейший работы с jsp
            model.addAttribute("photo_id", id);
            //Отправляет на страничку result
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    //Этот метод должен обрабатывать img теги
    //Тоесть в теге img у нас стоит URL в виде фото src="/photo/${photo_id} которую нам нужно отрисовать
    //Отрабатываем запросы по определенному шаблону ибо фото может быть много(чтобы для каждой метот не писать же)
    @RequestMapping("/photo/{photo_id}")
    // @PathVariable говорит что часть URL по этому пути нужно передать
    // в этот метод(в нашем случаии photo_id) по сути спринг врезает сюда конвертит в long
    // и переедает в метод в виде long примитива
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    //Отрабатывает когда запрашиваем показать фотку и вводим айдишник её
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    //  @PathVariable обозначает что туда нужно передать параметр с POST запроса с вот таким "photo_id" именем
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping("/delete/{photo_id}")
    //Снова берем ади и вырезаем из URL и передаем в метод(тут в long)
    public String onDelete(@PathVariable("photo_id") long id) {
        if (photos.remove(id) == null)
            throw new PhotoNotFoundException();
        else
            return "index";
    }

    //Метод чтобы отдавать фотку в байтовом виде в таком как ее получили
    //ResponseEntity это обертка вокруг объекта( ответом будет массив байтов )
    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        //Указываем что мы ему отдаем, в данном случаи чтобы он понимал что это фото
        headers.setContentType(MediaType.IMAGE_PNG);

        //указываем содержимое, заголовки, и нужный статус код
        //уходит фотка с таким контент тайпом
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
