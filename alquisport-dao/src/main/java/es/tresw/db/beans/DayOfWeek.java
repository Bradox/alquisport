package es.tresw.db.beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum DayOfWeek
{

	SUNDAY {
        @Override
        public int toCalendar() {
            return Calendar.SUNDAY;
        }

    },
    MONDAY {
        @Override
        public int toCalendar() {
            return Calendar.MONDAY;
        }
    },
    TUESDAY {
        @Override
        public int toCalendar() {
            return Calendar.TUESDAY;
        }
    },
    WEDNESDAY {
        @Override
        public int toCalendar() {
            return Calendar.WEDNESDAY;
        }
    },
    THURSDAY {
        @Override
        public int toCalendar() {
            return Calendar.THURSDAY;
        }
    },
    FRIDAY {
        @Override
        public int toCalendar() {
            return Calendar.FRIDAY;
        }
    },
    SATURDAY {
        @Override
        public int toCalendar() {
            return Calendar.SATURDAY;
        }
    };

    public abstract int toCalendar();

    public static DayOfWeek fromCalendarDay(int day) {

        for (DayOfWeek dayOfWeek : DayOfWeek.values())
        {
            if (dayOfWeek.toCalendar() == day) 
            {
                return dayOfWeek;
            }
        }

        throw new EnumConstantNotPresentException(DayOfWeek.class, "Unknown value ["+day+"]");
    }

    public static DayOfWeek getByDate(Date date) 
    {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return fromCalendarDay(calendar.get(Calendar.DAY_OF_WEEK));
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
    	DayOfWeek dw = DayOfWeek.getByDate((new Date()));
    	System.out.println(dw.toString());
    }

}


