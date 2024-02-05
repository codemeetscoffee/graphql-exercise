package com.cmc.graphql.component.problemz;

import com.course.graphql.DgsConstants;
import com.course.graphql.types.SearchItemFilter;
import com.course.graphql.types.SearchableItem;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class ItemSearchDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchItems(@InputArgument(name = "filter", collectionType = SearchItemFilter.class) SearchItemFilter filter){
        return null;
    }
}
