package com.tabnine.statusBar;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import com.tabnine.binary.BinaryRequestFacade;
import com.tabnine.lifecycle.BinaryInstantiatedActions;
import org.jetbrains.annotations.Nls;

import javax.annotation.Nonnull;

import static com.tabnine.general.DependencyContainer.instanceOfBinaryRequestFacade;
import static com.tabnine.general.DependencyContainer.instanceOfGlobalActionVisitor;

public class StatusBarPromotionProvider implements StatusBarWidgetFactory {
    private final BinaryRequestFacade binaryRequestFacade = instanceOfBinaryRequestFacade();
    private final BinaryInstantiatedActions actionVisitor = instanceOfGlobalActionVisitor();

    @Nonnull
    @Override
    public String getId() {
        return StatusBarPromotionWidget.class.getSimpleName();
    }

    @Nls
    @Nonnull
    @Override
    public String getDisplayName() {
        return "Tabnine Promition";
    }

    @Override
    public boolean isAvailable(@Nonnull Project project) {
        return true;
    }

    @Nonnull
    @Override
    public StatusBarWidget createWidget(@Nonnull Project project) {
        return new StatusBarPromotionWidget(project, binaryRequestFacade, actionVisitor);
    }

    @Override
    public void disposeWidget(@Nonnull StatusBarWidget statusBarWidget) {
        statusBarWidget.disposeWithTree();
    }

    @Override
    public boolean canBeEnabledOn(@Nonnull StatusBar statusBar) {
        return true;
    }
}
