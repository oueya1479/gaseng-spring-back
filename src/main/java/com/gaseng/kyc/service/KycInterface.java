package com.gaseng.kyc.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import com.gaseng.kyc.dto.Customer;

@Service
public class KycInterface {
    private final Web3j web3j;
    private final String contractAddress = "0xD69297b898f3540D0FDa0BE69F69c54E65043A89";
    private final String yourAddress = "0xBF87Db6bd55E7584f7a9b30aD93791D1cd4c3e70";
    private final Credentials credentials;
    private final DefaultGasProvider gasProvider = new DefaultGasProvider();

    public KycInterface(Web3j web3j, Credentials credentials) {
        this.web3j = web3j;
        this.credentials = credentials;
    }

    public Long setKyc(Long id, String name, String birth, String addr, String job) throws Exception {
    	KycContract contract = KycContract.load(contractAddress, web3j, credentials, gasProvider);

        TransactionReceipt transactionReceipt = contract.setKyc(
            BigInteger.valueOf(id),
            new Utf8String(name),
            new Utf8String(birth),
            new Utf8String(addr),
            new Utf8String(job)
        ).send();

        return id;
    }

    public Customer getKyc(Long id) {
        Function function = new Function("getKyc",
                List.of(new Uint256(id)),
                List.of(new TypeReference<Utf8String>() {},
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Utf8String>() {}));

        String encodedFunction = FunctionEncoder.encode(function);

        try {
            org.web3j.protocol.core.methods.response.EthCall response = web3j.ethCall(
                    Transaction.createEthCallTransaction(yourAddress, contractAddress, encodedFunction),
                    DefaultBlockParameterName.LATEST).send();

            List<Type> result = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
            
            String name = (String) result.get(0).getValue();
            String birth = (String) result.get(1).getValue();
            String addr = (String) result.get(2).getValue();
            String job = (String) result.get(3).getValue();

            return new Customer(name, birth, addr, job);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}