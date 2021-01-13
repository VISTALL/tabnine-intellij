package com.tabnine.lifecycle;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.WindowManager;
import com.tabnine.binary.BinaryRequestFacade;
import com.tabnine.binary.requests.statusBar.StatusBarPromotionBinaryRequest;
import com.tabnine.binary.requests.statusBar.StatusBarPromotionBinaryResponse;
import com.tabnine.binary.requests.statusBar.StatusBarPromotionShownRequest;
import com.tabnine.general.StaticConfig;
import com.tabnine.statusBar.StatusBarPromotionWidget;
import consulo.util.lang.ObjectUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author VISTALL
 * @since 13/01/2021
 * <p>
 * from kotlin
 */
public class BinaryPromotionStatusBarLifecycle {
    private final BinaryRequestFacade binaryRequestFacade;

    public BinaryPromotionStatusBarLifecycle(BinaryRequestFacade binaryRequestFacade) {
        this.binaryRequestFacade = binaryRequestFacade;
    }

    public void poll() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                StatusBarPromotionWidget.StatusBarPromotionComponent statusBarPromotionWidget = getPromotionWidget();

                StatusBarPromotionBinaryResponse promotion = binaryRequestFacade.executeRequest(new StatusBarPromotionBinaryRequest());

                if (promotion != null) {
                    statusBarPromotionWidget.setVisible(true);
                    statusBarPromotionWidget .setText(promotion.getMessage());
                    statusBarPromotionWidget.setId(promotion.getId());
                    statusBarPromotionWidget.setActions(promotion.getActions());
                    statusBarPromotionWidget.setNotificationType(promotion.getNotificationType());

                    binaryRequestFacade.executeRequest(new StatusBarPromotionShownRequest(ObjectUtil.notNull(promotion.getMessage(), "undefined")));
                }
                else {
                    clear(statusBarPromotionWidget);
                }
            }
        }, StaticConfig.BINARY_PROMOTION_POLLING_DELAY, StaticConfig.BINARY_PROMOTION_POLLING_INTERVAL);
    }

    public StatusBarPromotionWidget.StatusBarPromotionComponent getPromotionWidget() {
        Project[] openProjects = ProjectManager.getInstance().getOpenProjects();

        if (openProjects.length == 0) {
            return null;
        }

        StatusBar statusBar = WindowManager.getInstance().getStatusBar(openProjects[0]);
        if (statusBar == null) {
            return null;
        }

        StatusBarWidget widget = statusBar.getWidget(StatusBarPromotionWidget.class.getName());
        if (widget == null) {
            return null;
        }

        StatusBarPromotionWidget promotionWidget = (StatusBarPromotionWidget) widget;

        return (StatusBarPromotionWidget.StatusBarPromotionComponent) promotionWidget.getComponent();
    }

    private void clear(StatusBarPromotionWidget.StatusBarPromotionComponent statusBarPromotionWidget) {
        if (statusBarPromotionWidget == null) {
            return;
        }

        statusBarPromotionWidget.setText(null);
        statusBarPromotionWidget.setId(null);
        statusBarPromotionWidget.setVisible(false);
        statusBarPromotionWidget.setActions(null);
        statusBarPromotionWidget.setNotificationType(null);
    }
}
