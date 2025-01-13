package se.hackney.claude;

public enum Model {
    // Claude 3.5 models
    CLAUDE_3_5_SONNET(
        "claude-3-5-sonnet-20241022",
        "claude-3-5-sonnet-latest",
        "anthropic.claude-3-5-sonnet-20241022-v2:0",
        "claude-3-5-sonnet-v2@20241022"
    ),
    CLAUDE_3_5_HAIKU(
        "claude-3-5-haiku-20241022",
        "claude-3-5-haiku-latest",
        "anthropic.claude-3-5-haiku-20241022-v1:0",
        "claude-3-5-haiku@20241022"
    ),

    // Claude 3 models
    CLAUDE_3_OPUS(
        "claude-3-opus-20240229",
        "claude-3-opus-latest",
        "anthropic.claude-3-opus-20240229-v1:0",
        "claude-3-opus@20240229"
    ),
    CLAUDE_3_SONNET(
        "claude-3-sonnet-20240229",
        null, // no latest alias
        "anthropic.claude-3-sonnet-20240229-v1:0",
        "claude-3-sonnet@20240229"
    ),
    CLAUDE_3_HAIKU(
        "claude-3-haiku-20240307",
        null, // no latest alias
        "anthropic.claude-3-haiku-20240307-v1:0",
        "claude-3-haiku@20240307"
    );

    private final String anthropicId;
    private final String anthropicLatestAlias;
    private final String awsBedrockId;
    private final String gcpVertexAiId;

    Model(String anthropicId, String anthropicLatestAlias, String awsBedrockId, String gcpVertexAiId) {
        this.anthropicId = anthropicId;
        this.anthropicLatestAlias = anthropicLatestAlias;
        this.awsBedrockId = awsBedrockId;
        this.gcpVertexAiId = gcpVertexAiId;
    }

    public String getAnthropicId() {
        return anthropicId;
    }

    public String getAnthropicLatestAlias() {
        return anthropicLatestAlias;
    }

    public String getAwsBedrockId() {
        return awsBedrockId;
    }

    public String getGcpVertexAiId() {
        return gcpVertexAiId;
    }

    /**
     * Get the model ID for a specific platform
     * @param platform The platform to get the ID for
     * @return The model ID for the specified platform
     */
    public String getModelId(Platform platform) {
        return switch (platform) {
            case ANTHROPIC -> anthropicId;
            case ANTHROPIC_LATEST -> anthropicLatestAlias != null ? anthropicLatestAlias : anthropicId;
            case AWS_BEDROCK -> awsBedrockId;
            case GCP_VERTEX_AI -> gcpVertexAiId;
        };
    }

    public enum Platform {
        ANTHROPIC,
        ANTHROPIC_LATEST,
        AWS_BEDROCK,
        GCP_VERTEX_AI
    }
}