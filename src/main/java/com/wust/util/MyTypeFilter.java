package com.wust.util;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

public class MyTypeFilter implements TypeFilter {
    /**
     *
     * @param metadataReader:注解信息
     * @param metadataReaderFactory
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获得标注有注解的类的全类名
        String className = metadataReader.getClassMetadata().getClassName();
        //全类名中包含有"er"的进行过滤，否则不进行过滤
        if (className.contains("ce"))
            return true;
        return false;
    }
}
