package org.javers.core.pico;

import org.javers.common.pico.JaversModule;
import org.javers.core.Javers;
import org.javers.core.JaversCoreConfiguration;
import org.javers.core.commit.CommitFactory;
import org.javers.core.diff.DiffFactory;
import org.javers.core.diff.appenders.*;
import org.javers.core.json.JsonConverterBuilder;
import org.javers.core.metamodel.type.TypeFactory;
import org.javers.core.metamodel.type.TypeMapper;
import org.javers.model.object.graph.ObjectGraphBuilder;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Piotr Betkier
 */
public class CoreJaversModule implements JaversModule {

    private static final Class[] moduleComponents = new Class[]{
            Javers.class,
            DiffFactory.class,
            ObjectGraphBuilder.class,
            NewObjectAppender.class,
            MapChangeAppender.class,
            ListChangeAppender.class,
            SetChangeAppender.class,
            ArrayChangeAppender.class,
            ObjectRemovedAppender.class,
            ReferenceChangeAppender.class,
            JsonConverterBuilder.class,
            ValueChangeAppender.class,
            TypeMapper.class,
            TypeFactory.class,
            JaversCoreConfiguration.class,
            CommitFactory.class
    };

    @Override
    public Collection<Class> getModuleComponents() {
        return Arrays.asList(moduleComponents);
    }

}
