
select
    date_of_write_utility_meter,
    (cold_water * cold_water_cost) AS ColdPayment,
    round(CAST (hot_water AS numeric) * CAST(hot_water_cost AS numeric), 2) AS HotWaterPayment,
    electricity * electricity_cost AS ElectroPayment,
    gas * gas_cost AS GasPayment,
    capital_repair,
    house_utility

from ub_utilities, ub_utility_cost
WHERE utility_id=2;


