package be.pekket.housescraper.provider.realo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class RealoResponse {
    @JsonProperty("data")
    RealoData data;

    @Getter
    public class RealoData {
        @JsonProperty("list")
        RealoList list;

        @Getter
        public class RealoList {
            @JsonProperty("assigns")
            RealoAssigns assigns;

            @Getter
            public class RealoAssigns {
                @JsonProperty("componentEstateListGrid")
                RealoListGrid realoListGrid;

                @Getter
                public class RealoListGrid {
                    @JsonProperty("data")
                    RealoListData data;

                    @Getter
                    public class RealoListData {
                        @JsonProperty("items")
                        List<RealoHouse> items;
                    }
                }
            }
        }
    }
}