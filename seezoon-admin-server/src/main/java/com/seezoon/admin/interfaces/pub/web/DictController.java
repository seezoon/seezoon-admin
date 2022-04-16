package com.seezoon.admin.interfaces.pub.web;

import com.seezoon.admin.infrastructure.properties.DictProperties;
import com.seezoon.admin.infrastructure.properties.DictProperties.Dict;
import com.seezoon.web.api.Result;
import com.seezoon.web.controller.BaseController;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/dict")
@RequiredArgsConstructor
public class DictController extends BaseController {

    private final DictProperties dictProperties;

    @GetMapping("/get_dict")
    public Result<Map<String, List<Dict>>> getDict() {
        return Result.ok(dictProperties.getDicts());
    }

    @GetMapping("/get_dict/{name}")
    public Result<List<Dict>> getDictByName(@PathVariable String name) {
        return Result.ok(dictProperties.getDicts().get(name));
    }
}
