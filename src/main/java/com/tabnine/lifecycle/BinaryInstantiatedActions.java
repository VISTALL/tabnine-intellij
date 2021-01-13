package com.tabnine.lifecycle;

import com.tabnine.binary.BinaryRequestFacade;
import com.tabnine.binary.requests.config.ConfigRequest;

/**
 * @author VISTALL
 * @since 13/01/2021
 *
 * from kotlin
 */
public class BinaryInstantiatedActions {
  private BinaryRequestFacade binaryRequestFacade;

  public BinaryInstantiatedActions(BinaryRequestFacade binaryRequestFacade) {
    this.binaryRequestFacade = binaryRequestFacade;
  }

  public void openHub() {
    binaryRequestFacade.executeRequest(new ConfigRequest());
  }
}
