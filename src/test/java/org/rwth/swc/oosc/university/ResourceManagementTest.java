package org.rwth.swc.oosc.university;

import org.junit.jupiter.api.Test;
import org.rwth.swc.oosc.university.management.Resource;
import org.rwth.swc.oosc.university.management.ResourcesHelper;
import org.rwth.swc.oosc.university.rooms.Laboratory;
import org.rwth.swc.oosc.university.rooms.LectureHall;
import org.rwth.swc.oosc.university.rooms.Room;
import org.rwth.swc.oosc.university.rooms.SeminarRoom;
import org.rwth.swc.oosc.university.supplies.*;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourceManagementTest {

    private static final LocalDate TEST_DATE = LocalDate.of(2022, 12, 24);

    @Test
    public void stage1() {
        assertHierarchy(Room.class, Laboratory.class, true);
        assertHierarchy(Room.class, LectureHall.class, true);
        assertHierarchy(Room.class, SeminarRoom.class, true);
        assertHierarchy(Resource.class, Room.class, false);

        assertHierarchy(Supply.class, ChemistrySet.class, true);
        assertHierarchy(Supply.class, Notebook.class, true);
        assertHierarchy(Supply.class, RoomDivider.class, true);
        assertHierarchy(Supply.class, Tablet.class, true);
        assertHierarchy(Resource.class, Supply.class, false);
    }

//    @Test
//    public void stage2() {
//        Collection<Laboratory> laboratories = createResources(Laboratory.class);
//        Collection<LectureHall> lectureHalls = createResources(LectureHall.class);
//
//        Collection<Laboratory> freeLaboratories = ResourcesHelper.getFreeResources(laboratories, TEST_DATE);
//        assertEquals(10, freeLaboratories.size());
//
//        Collection<LectureHall> freeLectureHalls = ResourcesHelper.getFreeResources(lectureHalls, TEST_DATE);
//        assertEquals(10, freeLectureHalls.size());
//
//        Collection<Room> rooms = Stream.concat(laboratories.stream(), lectureHalls.stream()).toList();
//        Collection<Room> freeRooms = ResourcesHelper.getFreeResources(rooms, TEST_DATE);
//        assertEquals(20, freeRooms.size());
//
//
//        Collection<ChemistrySet> chemistrySets = createResources(ChemistrySet.class);
//        Collection<Tablet> tablets = createResources(Tablet.class);
//
//        Collection<ChemistrySet> freeChemistrySets = ResourcesHelper.getFreeResources(chemistrySets, TEST_DATE);
//        assertEquals(10, freeChemistrySets.size());
//
//        Collection<Tablet> freeTablets = ResourcesHelper.getFreeResources(tablets, TEST_DATE);
//        assertEquals(10, freeTablets.size());
//
//        Collection<Supply> supplies = Stream.concat(chemistrySets.stream(), tablets.stream()).toList();
//        Collection<Supply> freeSupplies = ResourcesHelper.getFreeResources(supplies, TEST_DATE);
//        assertEquals(20, freeSupplies.size());
//
//        Collection<Resource> resources = Stream.concat(laboratories.stream(), chemistrySets.stream()).toList();
//        Collection<Resource> freeResources = ResourcesHelper.getFreeResources(resources, TEST_DATE);
//        assertEquals(20, freeResources.size());
//
//        /* HINT: This should not compile! */
////        ResourcesHelper.getFreeResources(new ArrayList<Object>(), TEST_DATE);
//    }

    private void assertHierarchy(Class<?> A, Class<?> B, boolean instantiable) {
        assertTrue(A.isAssignableFrom(B), "Error in your inheritance hierarchy.");
        assertEquals(instantiable, !Modifier.isAbstract(B.getModifiers()));
    }

    protected static <T> T createResource(Class<T> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch(Exception ex) {
            throw new RuntimeException("Your Resource classes need to have a default constructor.");
        }
    }

    protected static <T> Collection<T> createResources(Class<T> clazz) {
        List<T> resources = new ArrayList<T>();
        for(int i = 0; i<10; i++) {
            resources.add(createResource(clazz));
        }
        return resources;
    }
}
