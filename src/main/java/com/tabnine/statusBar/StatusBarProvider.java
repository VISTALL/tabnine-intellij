package com.tabnine.statusBar;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import com.tabnine.binary.BinaryRequestFacade;
import com.tabnine.general.DependencyContainer;
import org.jetbrains.annotations.Nls;

import javax.annotation.Nonnull;

public class StatusBarProvider implements StatusBarWidgetFactory {
    private BinaryRequestFacade binaryRequestFacade = DependencyContainer.instanceOfBinaryRequestFacade();

    @Nonnull
    @Override
    public String getId() {
        return TabnineStatusBarWidget.class.getSimpleName();
    }

    @Nls
    @Nonnull
    @Override
    public String getDisplayName() {
        return "Tabnine";
    }

    @Override
    public boolean isAvailable(@Nonnull Project project) {
        return true;
    }

    @Nonnull
    @Override
    public StatusBarWidget createWidget(@Nonnull Project project) {
        return new TabnineStatusBarWidget(project, binaryRequestFacade);
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
