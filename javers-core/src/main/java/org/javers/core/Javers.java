package org.javers.core;

import org.javers.core.commit.Commit;
import org.javers.core.diff.Diff;
import org.javers.core.diff.DiffFactory;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.type.JaversType;
import org.javers.core.metamodel.type.TypeMapper;
import org.javers.model.object.graph.ObjectGraphBuilder;


/**
 * Facade to JaVers instance.<br/>
 * Should be constructed by {@link JaversBuilder} provided with your domain specific configuration.
 * <br/><br/>
 *
 * See {@link MappingDocumentation} to find out how to map your domain model
 *
 * @author bartosz walacik
 */
public class Javers {
    private final DiffFactory diffFactory;

    private final TypeMapper typeMapper;

    private final JsonConverter jsonConverter;

    /**
     * JaVers instance should be constructed by {@link JaversBuilder}
     */
    public Javers(DiffFactory diffFactory, TypeMapper typeMapper, JsonConverter jsonConverter) {
        this.diffFactory = diffFactory;
        this.typeMapper = typeMapper;
        this.jsonConverter = jsonConverter;
    }

    /**
     * <p>
     * Persists current version of given domain objects graph in JaVers repository.
     * All changes made on versioned objects are recorded,
     * new objects become versioned and its initial state is recorded.
     * </p>
     *
     * For any versioned object, you can:
     * <ul>
     *     <li/>  TODO
     * </ul>
     *
     * @param currentVersion domain objects graph
     */
    public Diff commit(String user, Object currentVersion){
        return null;
    }

    /**
     * <p>
     * Easiest way to calculate diff, just provide two versions of the same object graph.
     * Use it if you don't want to store domain objects history in JaVers repository.
     * </p>
     *
     * <p>
     * Diffs can be converted to JSON with {@link #toJson(Diff)} and stored in custom repository
     * </p>
     */
    public Diff compare(String user, Object oldVersion, Object currentVersion) {
        ObjectGraphBuilder leftGraph = new ObjectGraphBuilder(typeMapper);
        ObjectGraphBuilder rightGraph = new ObjectGraphBuilder(typeMapper);
        return diffFactory.create(user, leftGraph.buildGraph(oldVersion), rightGraph.buildGraph(currentVersion));
    }

    /**
     * Initial diff is a kind of snapshot of given domain objects.
     * Use it alongside with {@link #compare(String, Object, Object)}
     */
    public Diff initial(String user, Object newDomainObject) {
        ObjectGraphBuilder graph = new ObjectGraphBuilder(typeMapper);
        return diffFactory.createInitial(user, graph.buildGraph(newDomainObject));
    }


    /**
     * Use it alongside with {@link #compare(String, Object, Object)}
     */
    public String toJson(Diff diff) {
        return jsonConverter.toJson(diff);
    }


    JaversType getForClass(Class<?> clazz) {
        return typeMapper.getJaversType(clazz);
    }
}
