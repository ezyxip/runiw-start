package com.ezyxip.runiwstart.UI.admin.ads;

import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AdsScreen extends VerticalLayout {
    public AdsScreen(){
        MessageList messageList = new MessageList();
        MessageListItem item = new MessageListItem(
                "Здравствуйте, уважаемые коллеги, начинаем работать.",
                LocalDateTime.now().toInstant(ZoneOffset.UTC),
                "Большой Начальник"
        );
        messageList.setItems(item);
        add(messageList);
    }
}
