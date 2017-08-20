package com.mehran.linkstore.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mehran.linkstore.TestUtil;
import com.mehran.linkstore.data.database.LabelDAO;
import com.mehran.linkstore.data.database.LinkStoreDB;
import com.mehran.linkstore.data.models.Label;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by mehran on 20.08.17.
 */

@RunWith(AndroidJUnit4.class)
public class LabelTest {
    private LinkStoreDB linkStoreDB;
    private LabelDAO labelDAO;

    @Before
    public void start()
    {
        Context context = InstrumentationRegistry.getTargetContext();
        linkStoreDB = Room.inMemoryDatabaseBuilder(context, LinkStoreDB.class).build();
        labelDAO = linkStoreDB.labelDAO();
    }

    @After
    public void finish()
    {
        linkStoreDB.close();
    }

    @Test
    public void shouldAddLabel() throws Exception
    {
        Label label = TestUtil.createLabel("testLabelToAdd", "color");
        long id = labelDAO.insertLabel(label);
        label.setId(id);

        List<Label> labels = labelDAO.loadAllLabels();
        Label addedLabel = labels.get(0);

        assertThat(addedLabel, equalTo(label));
    }

    @Test
    public void shouldUpdateLabel() throws Exception
    {
        Label label = TestUtil.createLabel("testLabelToUpdate", "color");
        long id = labelDAO.insertLabel(label);
        label.setId(id);
        label.setColor("newColor");
        labelDAO.update(label);
        List<Label> labels = labelDAO.loadAllLabels();
        Label updatedLabel = labels.get(0);

        assertThat(updatedLabel, equalTo(label));
    }

    @Test
    public void shouldDeleteLabel() throws Exception
    {
        Label label = TestUtil.createLabel("testLabelToDelete", "color");
        long id = labelDAO.insertLabel(label);
        label.setId(id);
        label.setColor("newColor");
        labelDAO.delete(label);
        List<Label> labels = labelDAO.loadAllLabels();

        assertThat(labels.size(), equalTo(0));
    }
}
