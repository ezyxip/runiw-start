package com.ezyxip.runiwstart.UI.components;

import com.ezyxip.runiwstart.entities.MessageEntity;
import com.ezyxip.runiwstart.services.DataStorage;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class MessengerScreen extends VerticalLayout {
    public MessengerScreen(){
        MessageList messageList = new MessageList();

        List<MessageEntity> messages = DataStorage.messageRepository.findAll();

        ArrayList<MessageListItem> items = new ArrayList<>();

        messages.forEach(m -> items.add(0, new MessageListItem(m.getText(), m.getDate().toInstant(ZoneOffset.UTC), m.getAuthor())));

        messageList.setItems(items);

        MessageInput messageInput = new MessageInput();
        messageInput.addSubmitListener(e -> {
            MessageListItem newmess = new MessageListItem(e.getValue(),
                    LocalDateTime.now().toInstant(ZoneOffset.UTC),
                    SecurityContextHolder.getContext().getAuthentication().getName());
            items.add(0, newmess);
            messageList.setItems(items);
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
            messageEntity.setDate(LocalDateTime.now());
            messageEntity.setText(e.getValue());
            DataStorage.messageRepository.save(messageEntity);
        });


        messageList.getStyle().set("max-width", "400px");
        add(messageInput, messageList);
    }
}
