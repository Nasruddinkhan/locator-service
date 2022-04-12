package com.mypractice.sevices.impl;

import com.mypractice.model.dto.branches.OpenBankingBranch;
import com.mypractice.sevices.BranchService;
import com.mypractice.util.FileToDtoConvertorUtil;
import com.mypractice.util.Test;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
class RuleModel {
    private String promoValue;
    private String skuId;

    private String country;

    private String vpId;
    private Integer month;

    public Object clone() throws CloneNotSupportedException {
        return (RuleModel) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleModel ruleModel = (RuleModel) o;
        return skuId.equals(ruleModel.skuId) && vpId.equals(ruleModel.vpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuId, vpId);
    }
}

/**
 *
 */
@Service
public class BranchServiceImpl<R> implements BranchService {


    @Value("${resources.branchfilename}")
    private String branchFilePath;

    private final transient ResourceLoader resourceLoader;

    /**
     * @param resourceLoader
     */
    @Autowired
    public BranchServiceImpl(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * @return
     */
    @Override
    public OpenBankingBranch getAllBranches() {
        Resource resource = resourceLoader.getResource("classpath:" + "Rules_HP.txt");
        try (Stream<String> stream = Files.lines(Paths.get(resource.getURI())).skip(1)) {
            List<RuleModel> ruleModels = stream.map(obj -> obj.split("\t"))
                    .map(BranchServiceImpl::getRuleObject)
                    .map(BranchServiceImpl::getCountries).flatMap(List::stream)
                    .map(BranchServiceImpl::getVPIds)
                    .flatMap(List::stream)
                    .map(BranchServiceImpl::getMonthsVp)
                    .collect(Collectors.toList());
            resource = resourceLoader.getResource("classpath:" + "value_prop_file_HP.txt");
            var valueProps = Test.readValueProps(resource.getFile());

//            Lists.transform(valueProps, ValuePropModel::getVpid);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        try (Stream<String> stream = Files.lines(Paths.get(resource.getURI())).skip(1)) {
//            var rules = stream.map(obj -> obj.split("\t"))
//                    .map(BranchServiceImpl::getRuleObject)
//                    .map(e -> splitFileds(e, "country", e.getCountry())).flatMap(List::stream)
//                    .map(e -> splitFileds(e, "vpId", e.getVpId())).flatMap(List::stream)
//                    .collect(Collectors.toList());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return FileToDtoConvertorUtil.loadFile(resourceLoader, OpenBankingBranch.class, branchFilePath);
    }

    private static RuleModel getMonthsVp(RuleModel ruleModel) {
        ruleModel.setVpId(ruleModel.getMonth() > 0 ? ruleModel.getMonth() + "_MM_" + ruleModel.getVpId() : ruleModel.getVpId());
        return ruleModel;
    }

    private static List<RuleModel> getVPIds(RuleModel e) {
        String vpIds[] = e.getVpId().split(",");
        return Arrays.stream(vpIds).map(object -> {
            RuleModel ruleModel = new RuleModel();
            ruleModel.setMonth(e.getMonth());
            ruleModel.setCountry(object);
            ruleModel.setVpId(e.getVpId());
            ruleModel.setPromoValue(e.getPromoValue());
            ruleModel.setSkuId(e.getSkuId());
            return ruleModel;
        }).collect(Collectors.toList());
    }

    private static List<RuleModel> getCountries(RuleModel e) {
        String country[] = e.getCountry().split(",");
        return Arrays.stream(country).map(object -> {
            RuleModel ruleModel = new RuleModel();
            ruleModel.setMonth(e.getMonth());
            ruleModel.setCountry(object);
            ruleModel.setVpId(e.getVpId());
            ruleModel.setPromoValue(e.getPromoValue());
            ruleModel.setSkuId(e.getSkuId());
            return ruleModel;
        }).collect(Collectors.toList());
    }


    private static <R> List<R> splitFileds(R objClass, String fieldName, String value) {
        String[] values = value.split(",");
        return Arrays.stream(values).map(e -> {
            Field declaredField = null;
            try {
                System.out.println(objClass + "fieldName [" + fieldName + "] value [" + e + "]");
                declaredField = objClass.getClass()
                        .getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                declaredField.set(objClass, (Object) e);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            // System.out.println(objClass);
            return objClass;
        }).collect(Collectors.toList());

    }

    public static RuleModel getRuleObject(String[] arrayObject) {
        // System.out.println("BranchServiceImpl.getRuleObject" + arrayObject.length);
        RuleModel ruleModel = new RuleModel();
        ruleModel.setPromoValue(arrayObject[0]);
        ruleModel.setSkuId(arrayObject[1]);
        ruleModel.setCountry(arrayObject[2]);
        ruleModel.setVpId(arrayObject[3]);
        ruleModel.setMonth(arrayObject.length > 4 ?
                Integer.parseInt(arrayObject[4]) : 0);
        return ruleModel;
    }


}
