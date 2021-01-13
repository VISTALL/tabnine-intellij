package com.tabnine.lifecycle;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.tabnine.binary.BinaryRequestFacade;
import com.tabnine.binary.requests.notifications.BinaryNotification;
import com.tabnine.binary.requests.notifications.NotificationsBinaryRequest;
import com.tabnine.binary.requests.notifications.NotificationsBinaryResponse;
import com.tabnine.binary.requests.notifications.actions.NotificationActionRequest;
import com.tabnine.binary.requests.notifications.shown.NotificationShownRequest;
import com.tabnine.general.StaticConfig;
import consulo.ui.annotation.RequiredUIAccess;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author VISTALL
 * @since 13/01/2021
 */
public class BinaryNotificationsLifecycle {
    private final BinaryRequestFacade binaryRequestFacade;
    private final BinaryInstantiatedActions actionVisitor;

    public BinaryNotificationsLifecycle(BinaryRequestFacade binaryRequestFacade, BinaryInstantiatedActions actionVisitor) {
        this.binaryRequestFacade = binaryRequestFacade;
        this.actionVisitor = actionVisitor;
    }

    public void poll() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                NotificationsBinaryResponse request = binaryRequestFacade.executeRequest(new NotificationsBinaryRequest());

                if (request == null) {
                    return;
                }

                for (BinaryNotification binaryNotification : request.getNotifications()) {
                    if (PropertiesComponent.getInstance().getBoolean(storageKey(binaryNotification.getId()), false)) {
                        return;
                    }

                    Notification notification = new Notification(StaticConfig.BRAND_NAME, StaticConfig.NOTIFICATION_ICON, NotificationType.INFORMATION);

                    notification.setContent(binaryNotification.getMessage());

                    Optional.ofNullable(binaryNotification.getOptions())
                            .map(it -> it.stream())
                            .flatMap(it -> it.findFirst())
                            .ifPresent(o -> {
                                notification.addAction(new AnAction(o.getKey()) {
                                    @RequiredUIAccess
                                    @Override
                                    public void actionPerformed(@Nonnull AnActionEvent anActionEvent) {
                                        binaryRequestFacade.executeRequest(
                                                new NotificationActionRequest(
                                                        binaryNotification.getId(),
                                                        o.getKey(),
                                                        binaryNotification.getMessage(),
                                                        binaryNotification.getNotificationType(),
                                                        o.getActions()
                                                )
                                        );
                                        if (o.getActions().contains(StaticConfig.OPEN_HUB_ACTION)) {
                                            actionVisitor.openHub();
                                        }
                                        notification.expire();
                                    }
                                });
                            });

                    Notifications.Bus.notify(notification);
                    binaryRequestFacade.executeRequest(new NotificationShownRequest(binaryNotification.getMessage()));
                    PropertiesComponent.getInstance().setValue(storageKey(binaryNotification.getId()), true);
                }
            }
        }, StaticConfig.BINARY_NOTIFICATION_POLLING_INTERVAL, StaticConfig.BINARY_NOTIFICATION_POLLING_INTERVAL);
    }

    private String storageKey(String id) {
        return "tabnine-notifications-shown-" + id;
    }
}
