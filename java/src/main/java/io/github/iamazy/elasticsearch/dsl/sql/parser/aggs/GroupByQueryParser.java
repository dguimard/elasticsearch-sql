package io.github.iamazy.elasticsearch.dsl.sql.parser.aggs;


import com.google.common.collect.ImmutableList;
import io.github.iamazy.elasticsearch.dsl.sql.exception.ElasticSql2DslException;
import io.github.iamazy.elasticsearch.dsl.sql.parser.aggs.group.*;
import org.elasticsearch.search.aggregations.AggregationBuilder;

import java.util.List;

/**
 * @author iamazy
 * @date 2019-08-06
 * @descrition
 **/
public class GroupByQueryParser{

    public AggregationBuilder parse(String funcName,String field,Object... params){
        for(GroupByParser groupByParser:buildGroupByParserChain()){
            if(funcName.equalsIgnoreCase(groupByParser.funcName)){
                return groupByParser.parse(field,params);
            }
        }
        throw new ElasticSql2DslException("not support groupBy function named:"+funcName);
    }


    private static List<GroupByParser> buildGroupByParserChain(){
        return ImmutableList.of(
                new CountGroupByParser(),
                new AvgGroupByParser(),
                new MaxGroupByParser(),
                new MinGroupByParser(),
                new SumGroupByParser()
        );
    }
}
