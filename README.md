Im lazy to upload it on maven central or any libray repository, for now you need to move the class to your project.

# Documentation

## Using Tag

### Structure

Structre Tag is like this

```
<> = Required
[] = Optional
|| = Or
```

```xml
<<[style] || [foreground]> [background]>text
```

**Style**
> How your text looks like mybe bold or underline.

**Foreground**
> Color your text.

**Background**
> Background color of your text.

### Explaination
Style can be used as color and background is optional, here. I'll explain what possible.

**Text Color/Foreground**
> This will make your text colored but without any `background` or `style`.
```xml
<color>
```

Colored text but with background. If you want only background, i dont make it possible so just use like this `<default red>` or `<white red>`.
```xml
<color background>
```


**Text Style**
> When inside tag(<...>) name is not color name but `style` name like `bold` or `italic` will make the text stylize
```xml
<style>
```

**Reset**
> in style has really important style, it is `reset`. basicly you can reset color, style, or background text.
```xml
<reset>
```

### Style and Color names

**Style**
- reset
- bold
- dim
- italic
- underline

**Colo**
- black
- red
- green
- yellow
- blue
- magenta
- cyan
- white
- default

**Example in Java**

```java
import io.phanisment.ansicolor.ANSITag;

public class Main {
	public static void main(String[] args) {
		ANSITag.f("""
		<bold white red>ERROR:<reset> Unknown Mechanic 'jump'
		 <magenta>---><reset> <black>default:example.yml:12<reset>
		  |
		<magenta>0<reset> | <red>jump{s=1} @self<reset>
		  |
		  = <blue>suggest:<reset> <gray>Check if this typo or the mehcanic is doesnt exists
		""", System.out);
	}
}
```

## Using ANSIColor
This more simple and mybe less readable beacuse this method rely over `+` with string

**Example**

```java

println(ANSIColor.RED + "Hello world");

```

You can test this on your own ide using auto completion.