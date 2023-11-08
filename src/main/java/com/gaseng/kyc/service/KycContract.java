package com.gaseng.kyc.service;

import java.math.BigInteger;
import java.util.List;

import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;


class KycContract extends Contract {
    public static final String ABI = "[ { \"inputs\": [ { \"internalType\": \"uint256\", \"name\": \"id\", \"type\": \"uint256\" }, { \"internalType\": \"string\", \"name\": \"_name\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"_birth\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"_addr\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"_job\", \"type\": \"string\" } ], \"name\": \"setKyc\", \"outputs\": [], \"stateMutability\": \"nonpayable\", \"type\": \"function\" }, { \"inputs\": [], \"stateMutability\": \"nonpayable\", \"type\": \"constructor\" }, { \"inputs\": [], \"name\": \"admin\", \"outputs\": [ { \"internalType\": \"address\", \"name\": \"\", \"type\": \"address\" } ], \"stateMutability\": \"view\", \"type\": \"function\" }, { \"inputs\": [ { \"internalType\": \"uint256\", \"name\": \"\", \"type\": \"uint256\" } ], \"name\": \"customers\", \"outputs\": [ { \"internalType\": \"bytes\", \"name\": \"\", \"type\": \"bytes\" } ], \"stateMutability\": \"view\", \"type\": \"function\" }, { \"inputs\": [ { \"internalType\": \"bytes\", \"name\": \"data\", \"type\": \"bytes\" } ], \"name\": \"decode\", \"outputs\": [ { \"components\": [ { \"internalType\": \"string\", \"name\": \"name\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"birth\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"addr\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"job\", \"type\": \"string\" } ], \"internalType\": \"struct KYC.Customer\", \"name\": \"customer\", \"type\": \"tuple\" } ], \"stateMutability\": \"pure\", \"type\": \"function\" }, { \"inputs\": [ { \"components\": [ { \"internalType\": \"string\", \"name\": \"name\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"birth\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"addr\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"job\", \"type\": \"string\" } ], \"internalType\": \"struct KYC.Customer\", \"name\": \"customer\", \"type\": \"tuple\" } ], \"name\": \"encode\", \"outputs\": [ { \"internalType\": \"bytes\", \"name\": \"\", \"type\": \"bytes\" } ], \"stateMutability\": \"pure\", \"type\": \"function\" }, { \"inputs\": [ { \"internalType\": \"uint256\", \"name\": \"id\", \"type\": \"uint256\" } ], \"name\": \"getKyc\", \"outputs\": [ { \"internalType\": \"string\", \"name\": \"\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"\", \"type\": \"string\" }, { \"internalType\": \"string\", \"name\": \"\", \"type\": \"string\" } ], \"stateMutability\": \"view\", \"type\": \"function\" } ]";
    
    public KycContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        super(ABI, contractAddress, web3j, credentials, gasProvider);
    }

    public static KycContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        return new KycContract(contractAddress, web3j, credentials, gasProvider);
    }

    public RemoteCall<TransactionReceipt> setKyc(BigInteger id, Utf8String name, Utf8String birth, Utf8String addr, Utf8String job) {
        Function function = new Function(
            "setKyc",
            List.of(new Uint256(id), name, birth, addr, job),
            List.of()
        );
        return executeRemoteCallTransaction(function);
    }
}