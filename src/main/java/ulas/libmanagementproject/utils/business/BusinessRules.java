package ulas.libmanagementproject.utils.business;

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
