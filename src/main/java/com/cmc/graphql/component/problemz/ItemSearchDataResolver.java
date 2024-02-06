package com.cmc.graphql.component.problemz;

import com.cmc.graphql.service.ProblemQueryService;
import com.cmc.graphql.service.query.SolutionsQueryService;
import com.cmc.graphql.util.GraphQLBeanMapper;
import com.course.graphql.DgsConstants;
import com.course.graphql.types.SearchItemFilter;
import com.course.graphql.types.SearchableItem;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@DgsComponent
public class ItemSearchDataResolver {
    @Autowired
    private ProblemQueryService problemQueryService;
    @Autowired
    private SolutionsQueryService solutionsQueryService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchItems(@InputArgument(name = "filter", collectionType = SearchItemFilter.class) SearchItemFilter filter){
       var result = new ArrayList<SearchableItem>();
       var keyword = filter.getKeyword();

       var problemzByKeyword = problemQueryService.problemzByKeyword(keyword)
               .stream().map(GraphQLBeanMapper::mapToGraphql).toList();
       result.addAll(problemzByKeyword);

       var solutionzByKeyword = solutionsQueryService.solutionzByKeyword(keyword)
               .stream().map(GraphQLBeanMapper::mapToGraphql).toList();
       result.addAll(solutionzByKeyword);

       if(result.isEmpty()){
           throw new DgsEntityNotFoundException("No item with keyword " + keyword);
       }
       result.sort((Comparator.comparing(SearchableItem::getCreateDateTime).reversed()));

        return result;
    }
}
