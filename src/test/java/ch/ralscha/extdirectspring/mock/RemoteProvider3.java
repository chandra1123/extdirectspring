/**
 * Copyright 2010 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.ralscha.extdirectspring.mock;

import java.util.List;
import java.util.Locale;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.springframework.web.bind.annotation.RequestParam;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;

@Named
public class RemoteProvider3 {

  @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, entryClass = Row.class)
  public List<Row> create1(List<Row> rows) {
    return rows;
  }

  @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, entryClass = Row.class)
  public List<Row> create2(List<Row> rows, HttpServletResponse response, HttpServletRequest request, HttpSession session, Locale locale) {
    Assert.assertNotNull(response);
    Assert.assertNotNull(request);
    Assert.assertNotNull(session);
    Assert.assertEquals(Locale.ENGLISH, locale);

    return rows;
  }

  @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, entryClass = Row.class)
  public List<Row> update1(List<Row> rows) {
    return rows;
  }

  @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, entryClass = Row.class)
  public List<Row> update2(Locale locale, @RequestParam(value = "id") int id, List<Row> rows) {
    Assert.assertEquals(10, id);
    Assert.assertEquals(Locale.ENGLISH, locale);
    return rows;
  }

  @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, entryClass = Row.class)
  public List<Row> update3(List<Row> rows, @RequestParam(value = "id", defaultValue = "1") int id, HttpServletRequest servletRequest) {
    Assert.assertEquals(1, id);
    Assert.assertNotNull(servletRequest);
    return rows;
  }

  @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, entryClass = Row.class, group = "group2")
  public List<Row> update4(@RequestParam(value = "id", required = false) Integer id, List<Row> rows) {
    if (id == null) {
      Assert.assertNull(id);
    } else {
      Assert.assertEquals(Integer.valueOf(11), id);
    }
    return rows;
  }

  @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, entryClass = Integer.class, group = "group3")
  public List<Integer> destroy(List<Integer> rows) {
    return rows;
  }

  @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, group = "group3")
  public List<Integer> invalidMethod(List<Integer> rows) {
    return rows;
  }
}
