# demo

一个基于 Java 8 和 Maven 的简单示例项目。

## 运行

```powershell
mvn test
mvn package
java -cp target/demo-1.0-SNAPSHOT.jar com.example.demo.App
```

## 光伏财务测算

`com.example.demo.solar` 根据 `光伏.xlsx` 的主计算链实现分层测算：

1. 输入参数：基础、投资、成本、发电、电价、金融参数。
2. 固定项：税率参数、所得税率及分阶段检修费规则。
3. 中间计算：投资与可抵扣增值税、贷款还本付息、逐年发电收入、经营成本、增值税及附加、利润和所得税。
4. 输出：逐年结果、全投资与资本金现金流、IRR、NPV、静态及动态回收期。

入口类：

- `SolarCalculationController`：调用入口。
- `SolarCalculationService`：计算服务接口。
- `SolarCalculationServiceImpl`：完整计算实现。
- `SolarCalculationRequest.excelExample()`：Excel 例值参数。

源工作簿存在旧外部链接、失效命名区域和个别不一致公式。Java 实现保留其主要业务口径，并作以下统一处理：

- 贷款按等额本金足额偿还，避免源表最后一期本金与贷款总额不一致。
- 营业利润统一使用不含税收入，避免源表首期混用含税收入。
- 所得税税率作为固定参数，不在公式中硬编码。
- 国家、省、市补贴统一计入自用和上网电价；例值均为零，因此与源表例值一致。
- 动态回收期基于折现后税后现金流重新计算，不沿用源表中的 `#REF!`。
