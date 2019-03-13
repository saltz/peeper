package com.dane.peeper.domain.extensions;

import com.dane.peeper.domain.models.viewModels.ErrorViewModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class BindingResultExtension {
    public static List<ErrorViewModel> returnBindingErrorMessages(BindingResult bindingResult) {
        List<ErrorViewModel> collection = new ArrayList<>();
        for (ObjectError objErr : bindingResult.getAllErrors()) {
            collection.add(new ErrorViewModel("model_error", objErr.getDefaultMessage()));
        }
        return collection;
    }
}