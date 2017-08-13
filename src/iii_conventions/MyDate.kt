package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (other.year < this.year) {
            return 1
        } else if (other.year > this.year) {
            return -1
        } else {
            if (other.month < this.month) {
                return 1
            } else if (other.month > this.month) {
                return -1
            } else {
                if (other.dayOfMonth < this.dayOfMonth) {
                    return 1
                } else if (other.dayOfMonth > this.dayOfMonth) {
                    return -1
                } else {
                    return 0
                }
            }
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)
operator fun MyDate.plus(timeInterval: TimeInterval): MyDate {
    return this.addTimeIntervals(timeInterval, 1)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun contains(value: MyDate): Boolean {
        return value >= start && value <= endInclusive
    }

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            private var _cursor = start
            var cursor: MyDate
                get() = _cursor
                set(value) {
                    _cursor = value
                }

            override fun hasNext(): Boolean {
                return cursor <= endInclusive
            }

            override fun next(): MyDate {
                var result = cursor
                cursor = cursor.nextDay()
                return result
            }
        }
    }
}
