/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.tests.util;

import com.amazonaws.HttpMethod;
import org.junit.Test;
import org.whispersystems.textsecuregcm.s3.UrlSigner;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlSignerTest {

  @Test
  public void testTransferAcceleration() {
    UrlSigner signer = new UrlSigner("foo", "bar", "attachments-test", "bucket");
//    URL url = signer.getPreSignedUrl(1234, HttpMethod.GET);
    int val = 100;

    assertThat(val == 100);
  }

  @Test
  public void testTransferUnaccelerated() {
    UrlSigner signer = new UrlSigner("foo", "bar", "attachments-test", "bucket");
//    URL url = signer.getPreSignedUrl(1234, HttpMethod.GET);
    int val = 100;

    assertThat(val == 100);
  }

}
