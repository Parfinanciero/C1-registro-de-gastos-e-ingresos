package com.msvc.ai_scanner.controllers;

import com.msvc.ai_scanner.dto.request.BillDtoWoCreatedAt;
import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Type;
import com.msvc.ai_scanner.services.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bills")
@Tag(name = "BillControler", description = "Controlador encargado de las peticiones para el registro de gastos e ingresos")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Controlardor encargado de guardar los gasto e ingresos en la base de datos.",
            description = "este enpoint recibe un json con las datos de ingreso de los movimientos economicos del usuario para almacenarlos en la base de datos.",
            tags = {"almacenamiento de informacion en la base de datos"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "recibe un objeto json con los siguientes datos: " +
                            "comapñia, categoria, fecha del registro, tipo del registro(ingreso o gasto), monto, y id del usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BillDtoWoCreatedAt.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Solicitud exitosa.",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = Bill.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<Bill> createBill(@RequestBody @Valid BillDtoWoCreatedAt bill) {
        return new ResponseEntity<>(billService.save(bill), HttpStatus.CREATED);
    }

    @GetMapping("find/{id}")
    @Operation(
            summary = "Controlardor encargado de buscar una lista de registros por el usuario relacionado y un rango de fecha.",
            description = "Este enpoint recibe el identificador del usuario con una fecha de inicio y una fecha final para hacer una busqueda en base de datos con todos los registros que el usuario tiene relacionados entre esas dos fechas.",
            tags = {"Busqueda en base de datos de registros de un usuario en especifico."},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "recibe un objeto json con los siguientes datos: " +
                            "id del usuario relacionado, fecha de inicio y fecha de fin",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Solicitud exitosa.",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = Bill.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<Bill>> findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(@PathVariable Long id,
                                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime startDate,
                                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDate,
                                                                                               @RequestParam Type type
                                                                                               ) {
        return ResponseEntity.ok().body(billService.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(id, startDate, endDate, type));
    }

    @GetMapping("/all")
    @Operation(
            summary = "Controlardor encargado traer todos los registros.",
            description = "este enpoint trae todos los registros.",
            tags = {"Recuperacion de todos los registros en base de datos."},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Solicitud exitosa.",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = Bill.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<Bill>> findAll() {
        return ResponseEntity.ok(billService.readAll());
    }

    @GetMapping
    public ResponseEntity<?> pruebaDeploy(){
        return new ResponseEntity<>(Collections.singletonMap("respuesta", "hola santi feo"), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Controlardor encargado de eliminar registros de la base de datos.",
            description = "este enpoint recibe un Id relacionado algun movimientos economicos del usuario para eliminarlo de la base de datos.",
            tags = {"eliminar regitros"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "recibe el Id de un registro para eliminarlo",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Solicitud exitosa."
                    )
            }
    )
    public ResponseEntity<?>delete(@PathVariable String id){
        billService.delete(id);
        return ResponseEntity.ok().build();
    }
}
