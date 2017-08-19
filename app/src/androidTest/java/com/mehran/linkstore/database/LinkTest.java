package com.mehran.linkstore.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mehran.linkstore.TestUtil;
import com.mehran.linkstore.data.database.LinkDAO;
import com.mehran.linkstore.data.database.LinkStoreDB;
import com.mehran.linkstore.data.models.Link;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by mehran on 19.08.17.
 */

@RunWith(AndroidJUnit4.class)
public class LinkTest {
    private LinkDAO linkDAO;
    private LinkStoreDB linkStoreDB;

    @Before
    public void start()
    {
        Context context = InstrumentationRegistry.getTargetContext();
        linkStoreDB = Room.inMemoryDatabaseBuilder(context, LinkStoreDB.class).build();
        linkDAO = linkStoreDB.linkDAO();
    }

    @After
    public void finish()
    {
        linkStoreDB.close();
    }

    @Test
    public void shouldAddLink() throws Exception
    {
        Link link = TestUtil.createLink("testUrlToAdd", false, false);

        long id = linkDAO.insertLink(link);
        link.setId(id);
        Link[] links = linkDAO.loadAllLinks();
        Link addedLink = links[0];
        assertThat(addedLink, equalTo(link));
    }

    @Test
    public void shouldUpdateLink() throws Exception
    {
        Link link = TestUtil.createLink("testUrlToUpdate", false, false);

        long id = linkDAO.insertLink(link);
        link.setId(id);
        link.setRead(true);
        linkDAO.updateLink(link);

        Link[] links = linkDAO.loadAllLinks();
        Link updatedLink = links[0];
        assertThat(updatedLink, equalTo(link));
    }

    @Test
    public void shouldDeleteLink() throws Exception
    {
        Link link = TestUtil.createLink("testUrlToDelete", false, false);

        long id = linkDAO.insertLink(link);
        link.setId(id);
        linkDAO.deleteLink(link);
        Link[] links = linkDAO.loadAllLinks();
        assertThat(links.length, equalTo(0));
    }
}
