package com.tabnine.binary.requests.statusBar;

import com.tabnine.binary.BinaryRequest;
import com.tabnine.binary.requests.EmptyResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author VISTALL
 * @since 13/01/2021
 *
 * from kotlin
 */
public class StatusBarPromotionActionRequest implements BinaryRequest<EmptyResponse> {
  private String myId;
  private String mySelected;
  private List<String> myActions;

  public StatusBarPromotionActionRequest(String myId, String mySelected, List<String> myActions) {
    this.myId = myId;
    this.mySelected = mySelected;
    this.myActions = myActions;
  }

  @Override
  public Class<EmptyResponse> response() {
    return EmptyResponse.class;
  }

  @Override
  public Object serialize() {
    return Map.of("StatusBarAction", this);
  }

  public String getId() {
    return myId;
  }

  public void setId(String id) {
    myId = id;
  }

  public String getSelected() {
    return mySelected;
  }

  public void setSelected(String selected) {
    mySelected = selected;
  }

  public List<String> getActions() {
    return myActions;
  }

  public void setActions(List<String> actions) {
    myActions = actions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatusBarPromotionActionRequest that = (StatusBarPromotionActionRequest)o;
    return Objects.equals(myId, that.myId) &&
      Objects.equals(mySelected, that.mySelected) &&
      Objects.equals(myActions, that.myActions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(myId, mySelected, myActions);
  }
}
