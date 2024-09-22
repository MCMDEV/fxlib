# fxlib

A library to allow easy customization of all the visual garbage in Minecraft plugins.

## Maximal Example:

```yaml
effect:
  # Settings define how the effect should be displayed.
  settings:
    # If global is true, the effect will be displayed to all players within range.
    # If false, the effect will only be displayed to the player who triggered it.
    global: true
    # The radius defines how far away the effect can be seen. Only used if global is true.
    radius: 20
  # Parts define the individual components of the effect.
  # There is no limit to the number of parts that can be included.
  parts:
    - type: actionbar
      # The message to display.
      message: '<red>Hello!'
    - type: message
      # The message to display.
      message: 'Hello!'
    - type: particle
      # The particle to display.
      particle: 'minecraft:flame'
      # Moves the origin of the particle in the x, y, and z directions.  
      offsetX: 0.5
      offsetY: 0.5
      offsetZ: 0.5
      # Causes the particle to move in the x, y, and z directions.
      deltaX: 0.1
      deltaY: 0.1
      deltaZ: 0.1
      # The number of particles to display.
      count: 100
      # The extra numeric data value for the particle. Usually used for speed.
      extra: 0.1
    - type: sound
      # The sound to play.
      sound: 'minecraft:entity.experience_orb.pickup'
      # The origin of the sound. Can be 'master', 'music', 'record', 'weather', 'block', 'hostile', 'neutral', 'player', 'ambient', or 'voice'.
      source: 'master'
      # The volume of the sound. 1.0 is normal volume. Can be between 0.0 and 10.0.
      volume: 1.0
      # The pitch of the sound. 1.0 is normal pitch. Can be between 0.5 and 2.0.
      pitch: 1.2
    - type: title
      # The title to display.
      title: '<yellow>Title'
      # The subtitle to display.
      subtitle: '<red>Subtitle'
      times:
        # The number of seconds to fade in.
        fadeIn: 20
        # The number of seconds to stay.
        stay: 40
        # The number of seconds to fade out.
        fadeOut: 20
      # Global settings can be overridden for individual parts.
      settings:
        global: false
        radius: 10
```