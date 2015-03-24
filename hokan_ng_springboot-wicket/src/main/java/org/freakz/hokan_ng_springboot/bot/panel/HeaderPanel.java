package org.freakz.hokan_ng_springboot.bot.panel;

import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by Petri Airio on 20.3.2015.
 *
 */
@Slf4j
public class HeaderPanel extends Panel {

  public HeaderPanel(String id, WebPage page) {
    super(id);
    add(new LoggedInUserPanel());
  }

}
