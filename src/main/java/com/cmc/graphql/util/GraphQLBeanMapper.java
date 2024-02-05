package com.cmc.graphql.util;

import com.cmc.graphql.datasource.entity.Problemz;
import com.cmc.graphql.datasource.entity.Solutionz;
import com.cmc.graphql.datasource.entity.Userz;
import com.cmc.graphql.datasource.entity.UserzToken;
import com.course.graphql.types.*;
import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneOffset;
import java.util.List;

public class GraphQLBeanMapper {
    private static final PrettyTime PRETTY_TIME = new PrettyTime();
    private static final ZoneOffset ZONE_OFFSET =  ZoneOffset.ofHours(2);
    public static User mapToGraphql(Userz original) {
        var result = new User();
        var createdDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);

        result.setAvatar(original.getAvatar());
        result.setCreateDateTime(createdDateTime);
        result.setDisplayName(original.getDisplayName());
        result.setEmail(original.getEmail());
        result.setId(original.getId().toString());
        result.setUsername(original.getUsername());
        return result;}
    public static Problem mapToGraphql(Problemz original) {
        var result = new Problem();
        var createdDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        var author = mapToGraphql(original.getCreatedBy());
        var convertedSolutions = original.getSolutions().stream().map(GraphQLBeanMapper::mapToGraphql).toList();
        var tagList = List.of(original.getTags().split(","));

        result.setAuthor(author);
        result.setContent(original.getContent());
        result.setCreateDateTime(createdDateTime);
        result.setId(original.getId().toString());
        result.setSolutions(convertedSolutions);
        result.setTags(tagList);
        result.setTitle(original.getTitle());
        result.setSolutionCount(convertedSolutions.size());
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createdDateTime));

        return result;}
    public static Solution mapToGraphql(Solutionz original) {
        var result = new Solution();
        var createdDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        var author = mapToGraphql(original.getCreatedBy());
        var category = StringUtils.equalsIgnoreCase(original.getCategory(), SolutionCategory.EXPLANATION.toString())
                ? SolutionCategory.EXPLANATION : SolutionCategory.REFERENCE;

        result.setAuthor(author);
        result.setCategory(category);
        result.setContent(original.getContent());
        result.setCreateDateTime(createdDateTime);
        result.setId(original.getId().toString());
        result.setVoteAsGoodCount(original.getVoteGoodCount());
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createdDateTime));

        return result;}

    public static UserAuthToken mapToGraphql(UserzToken original){
        var result = new UserAuthToken();
        var expiryDateTime = original.getExpiryTimestamp().atOffset(ZONE_OFFSET);
        result.setAuthToken(original.getAuthToken());
        result.setExpiryTime(expiryDateTime);
        return result;
    }

}
