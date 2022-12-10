package org.rwth.swc.oosc.university.management;

import java.time.LocalDate;
import java.util.Collection;

public class ResourcesHelper {
    public static <T extends Resource> Collection<T> getFreeResources(Collection<T> resources, LocalDate date) {
       return resources;
    }
}
