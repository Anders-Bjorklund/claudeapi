package se.hackney.claude.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usage {
    private int input_tokens;
    private int cache_creation_input_tokens;
    private int cache_read_input_tokens;
    private int output_tokens;
}
