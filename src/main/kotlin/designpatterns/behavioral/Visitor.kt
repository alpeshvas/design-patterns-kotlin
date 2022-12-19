package designpatterns.behavioral

//The visitor patter is used to separate a relatively complex set of structured data classes from
//the functionality that may be performed upon the data that the hold
// In this case FixePriceContract, TimeAndMatrialContract (Slightly complxe set of structured data classes)
// is separated from the functionalities that is performed upon the data it holds- MonthlyCostReporter, YearlyCostReporter

interface ReportVisitable{
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(val costPerYear: Long): ReportVisitable{
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }
}

class TimeAndMaterialContract(val costPerHour: Long, val hours: Long): ReportVisitable{
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }
}
interface ReportVisitor<out R>{
    fun visit(contract: FixedPriceContract): R
    fun visit(contract: TimeAndMaterialContract): R
}

class MonthlyCostReportVisitor: ReportVisitor<Long>{
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear/12

    override fun visit(contract: TimeAndMaterialContract) = contract.costPerHour * contract.hours

}

class YearlyCostReportVisitor: ReportVisitor<Int>{
    override fun visit(contract: FixedPriceContract): Int = contract.costPerYear.toInt()

    override fun visit(contract: TimeAndMaterialContract) = contract.hours.toInt()
}

fun main() {
    val projectAlpha = FixedPriceContract(costPerYear = 1000)
    val projectGamma = TimeAndMaterialContract(hours = 150, costPerHour = 10)
    val projects = arrayOf(projectAlpha, projectGamma)
    val monthlyCostReportVisitor = MonthlyCostReportVisitor()

    val monthlyCost = projects.sumOf { it.accept(monthlyCostReportVisitor) }

    val yearlyReportVisitor = YearlyCostReportVisitor()
    val yearlyCost = projects.sumOf { it.accept(yearlyReportVisitor) }
    println("Yearly cost: $yearlyCost")

    println("Monthly cost: $monthlyCost")
}