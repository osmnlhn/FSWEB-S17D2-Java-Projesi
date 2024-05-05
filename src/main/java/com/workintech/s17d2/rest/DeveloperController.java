package com.workintech.s17d2.rest;

import com.workintech.s17d2.dto.DeveloperResponse;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.DeveloperFactory;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private Map<Integer, Developer> developers;
    private Taxable taxable;
    @Autowired
public DeveloperController(Taxable taxable){
    this.taxable=taxable;
    this.developers=new HashMap<>();
}
    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    }
    @PostMapping
    public DeveloperResponse create(@RequestBody Developer developer){
  Developer createDeveloper= DeveloperFactory.createDeveloper(developer,taxable);
  this.developers.put(createDeveloper.getId(),createDeveloper);
  return new DeveloperResponse(createDeveloper,"Create has done succesfully", HttpStatus.CREATED.value());
    }

    @GetMapping
    public List<Developer> getAll(){
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public DeveloperResponse get(@PathVariable("id") Integer id){
        Developer developer=developers.get(id);
        if (developer==null){
            return new DeveloperResponse(null,id+"idli deger bulunamadi",HttpStatus.NOT_FOUND.value());
        }
        return new DeveloperResponse(developer,"kayit bulundu",HttpStatus.OK.value());
    }
    @PutMapping("/{id}")
    public DeveloperResponse uptade(@PathVariable("id")Integer id,@RequestBody Developer developer){
        developer.setId(id);
        Developer newDeveloper=DeveloperFactory.createDeveloper(developer,taxable);
        developers.put(newDeveloper.getId(),newDeveloper);
        return new DeveloperResponse(newDeveloper,"update basarili",HttpStatus.OK.value());
    }

    @DeleteMapping("/{id}")
    public DeveloperResponse delete(@PathVariable("id") Integer id){
        Developer removeDeveloper=developers.get(id);
        developers.remove(id);
        return new DeveloperResponse(removeDeveloper,"Silme islemi Basarili",HttpStatus.NO_CONTENT.value());
    }
}
