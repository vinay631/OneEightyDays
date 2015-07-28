plotSingle <- function(yr, df) {
  dfsubset <- subset(df, year(date) == yr)
  calendarFlow(dfsubset$date, dfsubset$Change_pct, palette="redgreen", main=toString(yr))
}