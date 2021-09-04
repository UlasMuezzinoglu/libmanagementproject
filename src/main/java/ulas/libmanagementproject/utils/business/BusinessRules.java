package ulas.libmanagementproject.utils.business;

import lombok.var;
import ulas.libmanagementproject.utils.results.Result;

public class BusinessRules
{
    public static Result Run(Result... logics)
    {
        for (var logic:logics) {
            if (!logic.isSuccess())
            {
                return logic;
            }
        }
        return null;
    }
}
