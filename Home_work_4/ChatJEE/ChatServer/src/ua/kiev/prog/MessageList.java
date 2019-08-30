package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class MessageList {
	private static final MessageList msgList = new MessageList();
    private static final int LIMIT = 100;

    private final Gson gson;
	private final List<Message> list = new LinkedList<Message>();
	
	public static MessageList getInstance() {
		return msgList;
	}
  
  	private MessageList() {
		gson = new GsonBuilder().create();
	}

	//Ограничение по добавлению сообщений, когда 100 сообщений в списке, с 0 индекса удаляеться
	public synchronized void add(Message m) {
	    if (list.size() + 1 == LIMIT) {
	        list.remove(0);
        }
		list.add(m);
	}
	
	public synchronized String toJSON(int n) {
		if (n == list.size()) return null;
		return gson.toJson(new JsonMessages(list, n));
	}
}
