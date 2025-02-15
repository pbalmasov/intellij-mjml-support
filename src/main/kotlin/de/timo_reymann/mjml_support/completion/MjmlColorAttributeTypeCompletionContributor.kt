package de.timo_reymann.mjml_support.completion

import com.intellij.codeInsight.completion.*
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.util.ProcessingContext
import com.intellij.xml.util.ColorSampleLookupValue
import de.timo_reymann.mjml_support.api.MjmlAttributeInformation
import de.timo_reymann.mjml_support.api.MjmlAttributeType
import de.timo_reymann.mjml_support.model.getMjmlInfoFromAttributeValue

class MjmlColorAttributeTypeCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().inside(XmlAttributeValue::class.java),
            object : MjmlAttributeCompletionProvider(MjmlAttributeType.COLOR) {
                override fun provide(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet,
                    mjmlAttribute: MjmlAttributeInformation
                ) {
                    result.addAllElements(ColorSampleLookupValue.getColors()
                        .map { it.toLookupElement() })
                }
            })
    }
}
