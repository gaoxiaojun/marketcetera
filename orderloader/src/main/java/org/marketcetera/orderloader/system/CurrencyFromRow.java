package org.marketcetera.orderloader.system;

import org.marketcetera.orderloader.OrderParsingException;
import org.marketcetera.trade.Currency;
import org.marketcetera.trade.Instrument;
import org.marketcetera.trade.SecurityType;
import org.marketcetera.util.misc.ClassVersion;

/* $License$ */

/**
 * Implements extraction of a currency instrument from a row.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: CurrencyFromRow.java 16901 2014-05-11 16:14:11Z colin $
 * @since 2.4.0
 */
@ClassVersion("$Id: CurrencyFromRow.java 16901 2014-05-11 16:14:11Z colin $")
public class CurrencyFromRow
        extends InstrumentFromRow
{
    /* (non-Javadoc)
     * @see org.marketcetera.orderloader.system.InstrumentFromRow#canProcess(java.lang.String, int)
     */
    @Override
    protected boolean canProcess(String inHeader,
                                 int inIndex)
    {
        return false;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.orderloader.system.InstrumentFromRow#extract(org.marketcetera.orderloader.system.Row)
     */
    @Override
    protected Instrument extract(Row inRow)
            throws OrderParsingException
    {
        getSecurityType(inRow.getRow());
        String symbol = getSymbol(inRow.getRow());
        return symbol != null && (!symbol.trim().isEmpty()) ? new Currency(symbol) : null;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.core.instruments.DynamicInstrumentHandler#isHandled(java.lang.Object)
     */
    @Override
    protected boolean isHandled(Row inValue)
    {
        SecurityType secType = null;
        try {
            secType = getSecurityType(inValue.getRow());
        } catch (OrderParsingException ignore) {
        }
        return secType == null || SecurityType.Currency.equals(secType);
    }
}
