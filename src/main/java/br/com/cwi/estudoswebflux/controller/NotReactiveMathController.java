package br.com.cwi.estudoswebflux.controller;

import br.com.cwi.estudoswebflux.service.NotReactiveMathService;
import br.com.cwi.estudoswebflux.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("math")
public class NotReactiveMathController {

    @Autowired
    private NotReactiveMathService notReactiveMathService;

    @GetMapping("square/{input}")
    public Response findSquare(@PathVariable int input) {
        return this.notReactiveMathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public List<Response> multiplicationTable(@PathVariable int input) {
        return this.notReactiveMathService.multiplicationTable(input);
    }
}
