package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.ItemDTO;
import com.springbootacademy.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pos.dto.request.ItemPriceBalQtyUpdateDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pos.service.ItemService;
import com.springbootacademy.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin()
public class ItemController {
    @Autowired
    private ItemService itemService;

//    @PostMapping(path = "/save")
//    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
//        String message = itemService.saveItem(itemSaveRequestDTO);
//        return "saved";
//    }

//    @PostMapping(path = "/save")
//    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
//        String message = itemService.saveItem(itemSaveRequestDTO);
//        StandardResponse standardResponse = new StandardResponse(
//                201,
//                "Item saved successfull",
//                message
//        );
//        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
//            standardResponse, HttpStatus.CREATED
//        );
//        return response;
//    }

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String message = itemService.saveItem(itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Item saved successfull", itemSaveRequestDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/get-all-item")
    public ResponseEntity<StandardResponse> getItems() {
        List<ItemDTO> itemDTO = itemService.getItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemDTO), HttpStatus.OK
        );
    }

    @PutMapping(path = "/update-item-name-balQty", params = "id")
    public ResponseEntity<StandardResponse> updateItemPriceBalQty(@RequestParam(value = "id") int id, @RequestBody ItemPriceBalQtyUpdateDTO itemPriceBalQtyUpdateDTO) {
        String name = itemService.updateItemPriceBalQty(id, itemPriceBalQtyUpdateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Selling price and Balance Quality updated Successfully!!", name)
                , HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-id", params = "id")
    public ItemDTO getItemById(@RequestParam(value = "id") int itemId) {
        ItemDTO itemDTO = itemService.getItemById(itemId);
        return itemDTO;
    }

    @GetMapping(
            path = "/get-by-name-with-mapstruct",
            params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(@RequestParam(value = "name") String name){
        List<ItemGetResponseDTO> itemGetResponseDTOS = itemService.getItemByNameAndStatusByMapstruct(name);
        return itemGetResponseDTOS;

    }

    @DeleteMapping(path = "/delete-item/{id}")
    public ResponseEntity<StandardResponse> deleteItem(@PathVariable(value = "id")int id){
        itemService.deleteItemById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Item Deleted!!!",null),HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-items",params = {"page","size"})
    public ResponseEntity<StandardResponse> getAllItems(
            @RequestParam(value = "page")int page,
            @RequestParam(value = "size") @Max(50) int size){

        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItems(page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"items by paging",paginatedResponseItemDTO),HttpStatus.OK
        );
    }
    @GetMapping(path = "/get-items-by-status-paginated", params = {"page","size","status"})

    public ResponseEntity<StandardResponse> getItemsByStatus(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "status") boolean status
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemsByState(page,size,status);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"items by status",paginatedResponseItemDTO),HttpStatus.OK
        );
    }


}
