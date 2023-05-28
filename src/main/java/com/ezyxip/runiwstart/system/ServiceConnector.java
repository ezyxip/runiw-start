package com.ezyxip.runiwstart.system;

/**
 * Интерфейс, необходимый для поддержки сервсиной архитектуры.
 * Содержит в себе методы для авторизации модуля и пользователя.
 */
public interface ServiceConnector {
    void moduleLogin();
    String userLogin();
    boolean isUserAuth(String token);
}
