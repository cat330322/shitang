package com.ccc.controller;

import com.ccc.entity.Caigou;
import com.ccc.service.CaigouService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Caigou)表控制层
 *
 * @author makejava
 * @since 2021-08-19 21:51:39
 */
@RestController
@CrossOrigin
/*@RequestMapping("caigou")*/
public class CaigouController {

    @Resource
    private CaigouService caigouService;

    @GetMapping("/caigou")
    public Caigou caigou(Integer id){
        return this.caigouService.queryById(id);
    }
//查询所有
    @GetMapping("/caigous")
    public List<Caigou> caigous(){ return  caigouService.queryAllByLimit(0,Integer.MAX_VALUE);
    }
    @PostMapping("/caigou")
    public void add(@RequestBody Caigou caigou) {
        if (StringUtils.isEmpty(caigou.getId())) {
            caigouService.insert(caigou);
        } else {
            caigouService.update(caigou);
        }
    }
}
