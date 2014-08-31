/*
 * Copyright (C) 2014 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.flossware.common.IntegrityUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the IntegrityUtil class.
 *
 * @author Scot P. Floess
 */
public class IntegrityUtilTest {

    /**
     * Used to test the generic ensure method.
     */
    private static final class Foo {
    }

    @Test
    public void testEnsure_Generic_success() {
        final Foo f = new Foo();

        Assert.assertEquals("Should be the same string!", f, IntegrityUtil.ensure(f, "Blah"));

        Assert.assertEquals("Should be the same string!", f, IntegrityUtil.ensure(f));
    }

    @Test
    public void testEnsure_Generic_failMsg() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            final Foo f = null;

            IntegrityUtil.ensure(f, msg);

            Assert.fail("A null should have failed!");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Generic_fail() {
        try {
            final Foo f = null;

            IntegrityUtil.ensure(f);

            Assert.fail("A null should have failed!");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_String_success() {
        final String str = TestUtil.generateUniqueStr("Test");

        Assert.assertEquals("Should be the same string!", str, IntegrityUtil.ensure(str, "Blah"));

        Assert.assertEquals("Should be the same string!", str, IntegrityUtil.ensure(str));
    }

    @Test
    public void testEnsure_String_emptyString_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure("", msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_String_emptyString_fail() {
        try {
            IntegrityUtil.ensure("");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_String_null_fail() {
        final String str = null;

        try {
            IntegrityUtil.ensure(str);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_minSize_success() {
        final List<String> list = new LinkedList<>();

        list.add(TestUtil.generateUniqueStr());
        list.add(TestUtil.generateUniqueStr());

        Assert.assertEquals("Should be same list", list, IntegrityUtil.ensure(list, list.size(), "Blah"));
        Assert.assertEquals("Should be same list", list, IntegrityUtil.ensure(list, list.size()));
    }

    @Test
    public void testEnsure_List_minSize_nullElement_msg_fail() {
        final List<String> list = new LinkedList<>();

        list.add(TestUtil.generateUniqueStr());
        list.add(null);
        list.add(TestUtil.generateUniqueStr());

        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            final List l = null;
            IntegrityUtil.ensure(list, list.size(), msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_minSize_nullElement_fail() {
        final List<String> list = new LinkedList<>();

        list.add(TestUtil.generateUniqueStr());
        list.add(null);
        list.add(TestUtil.generateUniqueStr());

        try {
            IntegrityUtil.ensure(list, list.size());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_minSize_null_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            final List l = null;
            IntegrityUtil.ensure(l, 1, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_minSize_null_fail() {
        try {
            final List l = null;
            IntegrityUtil.ensure(l, 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_minSize_empty_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(new LinkedList(), 2, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_minSize_empty_fail() {
        try {
            IntegrityUtil.ensure(new LinkedList(), 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_success() {
        final List<String> list = new LinkedList<>();

        list.add(TestUtil.generateUniqueStr());
        list.add(TestUtil.generateUniqueStr());

        Assert.assertEquals("Should be same list", list, IntegrityUtil.ensure(list, "Blah"));
        Assert.assertEquals("Should be same list", list, IntegrityUtil.ensure(list));
    }

    @Test
    public void testEnsure_List_nullElement_msg_fail() {
        final List<String> list = new LinkedList<>();

        list.add(TestUtil.generateUniqueStr());
        list.add(null);
        list.add(TestUtil.generateUniqueStr());

        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(list, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_nullElement_fail() {
        final List<String> list = new LinkedList<>();

        list.add(TestUtil.generateUniqueStr());
        list.add(null);
        list.add(TestUtil.generateUniqueStr());

        try {
            IntegrityUtil.ensure(list);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_null_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            final List l = null;
            IntegrityUtil.ensure(l, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_null_fail() {
        try {
            final List l = null;
            IntegrityUtil.ensure(l);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_empty_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(new LinkedList(), msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_List_empty_fail() {
        try {
            IntegrityUtil.ensure(new LinkedList());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Map_minSize_success() {
        final Map<String, String> map = new HashMap<>();

        map.put(TestUtil.generateUniqueStr(), TestUtil.generateUniqueStr());
        map.put(TestUtil.generateUniqueStr(), TestUtil.generateUniqueStr());

        Assert.assertEquals("Should be same list", map, IntegrityUtil.ensure(map, map.size(), "Blah"));
        Assert.assertEquals("Should be same list", map, IntegrityUtil.ensure(map, map.size()));
    }

    @Test
    public void testEnsure_Map_minSize_null_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            final Map m = null;
            IntegrityUtil.ensure(m, 1, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Map_minSize_null_fail() {
        try {
            final Map m = null;
            IntegrityUtil.ensure(m, 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Map_minSize_empty_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(new HashMap(), 2, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Map_minSize_empty_fail() {
        try {
            IntegrityUtil.ensure(new HashMap(), 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Map_success() {
        final Map<String, String> map = new HashMap<>();

        map.put(TestUtil.generateUniqueStr(), TestUtil.generateUniqueStr());
        map.put(TestUtil.generateUniqueStr(), TestUtil.generateUniqueStr());

        Assert.assertEquals("Should be same list", map, IntegrityUtil.ensure(map, "Blah"));
        Assert.assertEquals("Should be same list", map, IntegrityUtil.ensure(map));
    }

    @Test
    public void testEnsure_Map_null_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            final Map m = null;
            IntegrityUtil.ensure(m, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Map_null_fail() {
        try {
            Map m = null;
            IntegrityUtil.ensure(m);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Map_empty_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(new HashMap(), msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Map_empty_fail() {
        try {
            IntegrityUtil.ensure(new HashMap());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_long_success() {
        final long l = System.currentTimeMillis();

        IntegrityUtil.ensure(l, 1, "Blah");
        IntegrityUtil.ensure(l, 1);
    }

    @Test
    public void testEnsure_long_minValue_msg_fail() {
        final long l = 1;
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(l, System.currentTimeMillis(), msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_long_minValue_fail() {
        final long l = 1;

        try {
            IntegrityUtil.ensure(l, System.currentTimeMillis());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_int_success() {
        final int i = Math.abs((int) System.currentTimeMillis());

        IntegrityUtil.ensure(i, 1, "Blah");
        IntegrityUtil.ensure(i, 1);
    }

    @Test
    public void testEnsure_int_minValue_msg_fail() {
        final int i = 1;
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(i, Math.abs((int) System.currentTimeMillis()), msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_int_minValue_fail() {
        final int i = 1;

        try {
            IntegrityUtil.ensure(i, Math.abs((int) System.currentTimeMillis()));
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_minSize_success() {
        final String[] str = new String[2];

        str[0] = TestUtil.generateUniqueStr();
        str[1] = TestUtil.generateUniqueStr();

        Assert.assertSame("Should be same array", str, IntegrityUtil.ensure(str, str.length, "Blah"));
        Assert.assertSame("Should be same array", str, IntegrityUtil.ensure(str, str.length));
    }

    @Test
    public void testEnsure_Array_minSize_nullElement_msg_fail() {
        final String[] str = new String[3];

        str[0] = TestUtil.generateUniqueStr();
        str[2] = TestUtil.generateUniqueStr();

        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(str, str.length, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_minSize_nullElement_fail() {
        final String[] str = new String[3];

        str[0] = TestUtil.generateUniqueStr();
        str[2] = TestUtil.generateUniqueStr();

        try {
            IntegrityUtil.ensure(str, str.length);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_minSize_null_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            final String[] str = null;
            IntegrityUtil.ensure(str, 1, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_minSize_null_fail() {
        try {
            final String[] str = null;
            IntegrityUtil.ensure(str, 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_minSize_empty_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(new String[0], 2, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_minSize_empty_fail() {
        try {
            IntegrityUtil.ensure(new String[0], 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_success() {
        final String[] str = new String[2];

        str[0] = TestUtil.generateUniqueStr();
        str[1] = TestUtil.generateUniqueStr();

        Assert.assertSame("Should be same list", str, IntegrityUtil.ensure(str, "Blah"));
        Assert.assertSame("Should be same list", str, IntegrityUtil.ensure(str));
    }

    @Test
    public void testEnsure_Array_nullElement_msg_fail() {
        final String[] str = new String[3];

        str[0] = TestUtil.generateUniqueStr();
        str[2] = TestUtil.generateUniqueStr();

        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(str, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_nullElement_fail() {
        final String[] str = new String[3];

        str[0] = TestUtil.generateUniqueStr();
        str[2] = TestUtil.generateUniqueStr();

        try {
            IntegrityUtil.ensure(str);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_null_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            final String[] str = null;
            IntegrityUtil.ensure(str, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_null_fail() {
        try {
            final String[] str = null;
            IntegrityUtil.ensure(str);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_empty_msg_fail() {
        final String msg = TestUtil.generateUniqueStr("You", "fail");

        try {
            IntegrityUtil.ensure(new String[0], msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    @Test
    public void testEnsure_Array_empty_fail() {
        try {
            IntegrityUtil.ensure(new String[0]);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IntegrityUtil.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

}
