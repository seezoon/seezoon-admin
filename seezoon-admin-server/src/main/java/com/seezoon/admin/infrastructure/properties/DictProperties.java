package com.seezoon.admin.infrastructure.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seezoon.web.i18n.LocaleFactory;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "seezoon")
public class DictProperties {

    private Map<String, List<Dict>> dicts = new HashMap<>();

    @Getter
    @Setter

    public static class Dict {

        private Serializable value;
        private String label;
        @JsonIgnore
        private String zhLabel;
        @JsonIgnore
        private String enLabel;
        private boolean disabled;
        private String color;

        public String getLabel() {
            Locale locale = LocaleFactory.getCurrent();
            if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                return zhLabel;
            }
            return enLabel;
        }
    }
}
