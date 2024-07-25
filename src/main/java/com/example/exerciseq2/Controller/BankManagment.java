package com.example.exerciseq2.Controller;

import com.example.exerciseq2.API.ApiRespons;
import com.example.exerciseq2.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/v1/bank")
public class BankManagment {

    ArrayList<Customers> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customers> getCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public ApiRespons addCustomers(@RequestBody Customers customer) {
        customers.add(customer);
        return new ApiRespons("Customer added");
    }

    @PutMapping("/update/{index}")
    public ApiRespons updateCustomer(@PathVariable int index, @RequestBody Customers customer) {
        customers.set(index, customer);
        return new ApiRespons("Update is success");
    }

    @DeleteMapping("/delete/{index}")
    public ApiRespons deleteCustomer(@PathVariable int index) {
        customers.remove(index);
        return new ApiRespons("delete Success");

    }

    @PutMapping("addm/{index}/{money}")
    public ApiRespons DepositMoney(@PathVariable int index, @PathVariable double money) {
        customers.get(index).setBalance(money + customers.get(index).getBalance());
        return new ApiRespons("The deposit was successful");
    }

    @PutMapping("withdraw/{index}/{money}")
    public ApiRespons Withdraw(@PathVariable int index, @PathVariable double money) {
        if (customers.get(index).getBalance() >= money) {
            customers.get(index).setBalance(customers.get(index).getBalance() - money);
             return new ApiRespons("Success");
        }  else
            return new ApiRespons("The money is not enough for withdrawal");
    }

}




