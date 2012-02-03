package es.tresw.db.types;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum MonthName
{

	JANUARY {
        @Override
        public int toCalendar() {
            return Calendar.SUNDAY;
        }

    },
    FEBRUARY{
        @Override
        public int toCalendar() {
            return Calendar.JANUARY;
        }
    },
    MARCH {
        @Override
        public int toCalendar() {
            return Calendar.FEBRUARY;
        }
    },
    APRIL {
        @Override
        public int toCalendar() {
            return Calendar.MARCH;
        }
    },
    MAY {
        @Override
        public int toCalendar() {
            return Calendar.MAY;
        }
    },
    JUNE {
        @Override
        public int toCalendar() {
            return Calendar.JUNE;
        }
    },
    JULY {
        @Override
        public int toCalendar() {
            return Calendar.JULY;
        }
    },
    AUGUST {
        @Override
        public int toCalendar() {
            return Calendar.AUGUST;
        }
    },
    SEPTEMBER {
        @Override
        public int toCalendar() {
            return Calendar.SEPTEMBER;
        }
    },
    OCTOBER {
        @Override
        public int toCalendar() {
            return Calendar.OCTOBER;
        }
    },
    NOVEMBER {
        @Override
        public int toCalendar() {
            return Calendar.NOVEMBER;
        }
    },
    DECEMBER {
        @Override
        public int toCalendar() {
            return Calendar.DECEMBER;
        }
    };





    public abstract int toCalendar();

    public static MonthName fromCalendarMonth(int month) {

        for (MonthName monthName : MonthName.values())
        {
            if (monthName.toCalendar() == month) 
            {
                return monthName;
            }
        }

        throw new EnumConstantNotPresentException(MonthName.class, "Unknown value ["+month+"]");
    }

    public static MonthName getByDate(Date date) 
    {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return fromCalendarMonth(calendar.get(Calendar.MONTH));
    }

    @Override
    /*
     * Should return the localized day of the week
     */
    public String toString() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.DAY_OF_WEEK, this.toCalendar());
        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat
                .getInstance();
        sdf.applyPattern("EEEEEEEEEE");

        return sdf.format(c.getTime());
    }
    
    public static void main  (String[] args)
    {
    	MonthName dw = MonthName.getByDate((new Date()));
    	System.out.println(dw.toString());
    }

}


