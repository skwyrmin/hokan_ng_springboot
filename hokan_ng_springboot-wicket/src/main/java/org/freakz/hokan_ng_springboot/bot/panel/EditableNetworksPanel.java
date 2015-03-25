package org.freakz.hokan_ng_springboot.bot.panel;

import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.freakz.hokan_ng_springboot.bot.Services;
import org.freakz.hokan_ng_springboot.bot.jpa.entity.Network;
import org.wicketstuff.egrid.EditableGrid;
import org.wicketstuff.egrid.column.AbstractEditablePropertyColumn;
import org.wicketstuff.egrid.column.RequiredEditableTextFieldColumn;
import org.wicketstuff.egrid.provider.EditableListDataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petri Airio on 25.3.2015.
 */
@Slf4j
public class EditableNetworksPanel extends Panel {

  private FeedbackPanel feedbackPanel;

  public EditableNetworksPanel(String id) {
    super(id);
    setOutputMarkupId(true);

    feedbackPanel = new FeedbackPanel("feedBack");
    feedbackPanel.setOutputMarkupPlaceholderTag(true);
    add(feedbackPanel);

    EditableListDataProvider<Network, String> provider = new EditableListDataProvider<>(Services.getNetworkService().findAll());
    EditableGrid editableGrid = new EditableGrid<Network, String>("grid", getColumns(), provider, 10, Network.class) {
      private static final long serialVersionUID = 1L;

      @Override
      protected void onAdd(AjaxRequestTarget target, Network newRow) {
        Network network = Services.getNetworkService().create(newRow.getName());
        newRow.setId(network.getId());
        info("Added!");
        target.add(feedbackPanel);
      }

      @Override
      protected void onError(AjaxRequestTarget target) {
        log.debug("error");
        target.add(feedbackPanel);
      }

      @Override
      protected void onCancel(AjaxRequestTarget target) {
        log.debug("cancel");
        target.add(feedbackPanel);
      }

      @Override
      protected void onDelete(AjaxRequestTarget target, IModel<Network> rowModel) {
        Services.getNetworkService().delete(rowModel.getObject());
        log.debug("deleted");
        target.add(feedbackPanel);
      }

      @Override
      protected void onSave(AjaxRequestTarget target, IModel<Network> rowModel) {
        log.debug("save");
        Services.getNetworkService().save(rowModel.getObject());
        target.add(feedbackPanel);
      }
    };
    add(editableGrid);

  }

  private List<AbstractEditablePropertyColumn<Network, String>> getColumns() {
    List<AbstractEditablePropertyColumn<Network, String>> columns = new ArrayList<>();
    columns.add(new RequiredEditableTextFieldColumn<Network, String>(new Model<String>("ID"), "id"));
    columns.add(new RequiredEditableTextFieldColumn<Network, String>(new Model<String>("Network name"), "networkName"));
    return columns;
  }


}
