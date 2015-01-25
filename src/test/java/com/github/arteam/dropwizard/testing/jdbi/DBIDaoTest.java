package com.github.arteam.dropwizard.testing.jdbi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.HandleCallback;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.util.StringMapper;

import javax.management.monitor.StringMonitor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Date: 1/22/15
 * Time: 8:57 PM
 *
 * @author Artem Prigoda
 */
@RunWith(DBIRunner.class)
public class DBIDaoTest {

    @DBIHandle
    Handle handle;

    @TestedDBIDao
    PlayerDao playerDao;

    private final String helloDBI = "Hello DBI!";

    @Test
    public void testHelloWorld() {
        System.out.println("Hello world!");
    }

    @Test
    public void testInsert() throws Exception {
        System.out.println(helloDBI);
        Long playerId = playerDao.createPlayer("Vladimir", "Tarasenko", new SimpleDateFormat("yyyy-MM-dd HH:mm:SS")
                .parse("1991-08-05 00:00:00"), 84, 99);
        System.out.println(playerId);

        String initials = handle.createQuery("select first_name || ' ' || last_name from players")
                .map(StringMapper.FIRST)
                .first();
        System.out.println(initials);
        Assert.assertEquals(initials, "Vladimir Tarasenko");
    }


    @Test
    public void testGetInitials() {
        List<String> lastNames = playerDao.getLastNames();
        System.out.println(lastNames);
        Assert.assertTrue(lastNames.isEmpty());
    }
}