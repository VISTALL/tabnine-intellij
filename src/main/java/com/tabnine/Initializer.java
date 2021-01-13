package com.tabnine;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.ide.ApplicationLoadListener;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBusConnection;
import com.tabnine.lifecycle.BinaryNotificationsLifecycle;
import com.tabnine.lifecycle.BinaryPromotionStatusBarLifecycle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.tabnine.general.DependencyContainer.instanceOfBinaryNotifications;
import static com.tabnine.general.DependencyContainer.instanceOfBinaryPromotionStatusBar;

public class Initializer {
  private final BinaryNotificationsLifecycle binaryNotificationsLifecycle = instanceOfBinaryNotifications();
  private final BinaryPromotionStatusBarLifecycle binaryPromotionStatusBarLifecycle = instanceOfBinaryPromotionStatusBar();

  public Initializer() {
    binaryNotificationsLifecycle.poll();
    binaryPromotionStatusBarLifecycle.poll();
  }
}